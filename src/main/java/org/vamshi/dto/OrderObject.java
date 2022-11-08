package org.vamshi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderObject {
    private String name;
    private String orderId;
    private String itemId;
    private String vendorId;
    private String timestamp;
}
