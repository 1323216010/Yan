package com.yan.common.core.exception.user;

import com.yan.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author yan
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
