package net.alibi.projectDemo.masssage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseFile {
    private String id;
    private String name;
    private String url;
    private String type;
    private long size;

}
