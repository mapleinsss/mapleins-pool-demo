package org.maple.demo01;


import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * 兽人苦工
 */
@Data
@Log4j2
public class Peon {

    /**
     * 苦工编号
     */
    private String id;

    public void work() {
        log.debug("{} is doing the job", id);
    }

}
