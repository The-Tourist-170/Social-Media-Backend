package com.tourist.toddle.Service;

import com.tourist.toddle.Models.Cmt;

public interface CmtService {
    public Cmt newCmt(Cmt cmt, Integer pid, Integer uid) throws Exception;
    public Cmt findCmtById(Integer cmtId) throws Exception;
    public Cmt likedCmt(Integer cmtId, Integer uid) throws Exception;    
}
