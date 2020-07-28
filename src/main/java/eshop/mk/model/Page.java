package eshop.mk.model;

import eshop.mk.exceptions.InvalidPageException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Page<T> {
    private int page;

    private int totalPages;

    private int pageSize;

    private long totalElements;

    private List<T> content;

    public static <T> Page<T> slice(List<T> content, int page, int pageSize) {
        int pageStart = page * pageSize;
        int pageEnd = (page + 1) * pageSize;

        if (pageStart > content.size() || pageStart < 0 || pageEnd < 0) {
            throw new InvalidPageException();
        }
        if (pageEnd > content.size()) {
            pageEnd = content.size();
        }

        return new Page<>(page,
                (int) Math.ceil(1.0 * content.size() / pageSize),
                pageSize,
                content.size(),
                content.subList(pageStart, pageEnd));
    }
}
