head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.20.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3DefaultPreLoaderParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3DefaultPreLoaderParamsクラス(WEB3DefaultPreLoaderParams.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/24 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader;

import com.fitechlabs.dbind.RowType;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3DefaultPreLoaderParams
{
	
    private static final WEB3DefaultPreLoaderParams DEFALUT_PARAMS =
        new WEB3DefaultPreLoaderParams(null, null, null, null);
    
    private final RowType rowType;

    private final String where;

    private final String orderBy;

    private final String conditions;

    private final Object[] bindVars;

    public WEB3DefaultPreLoaderParams(
        String l_strWhere,
        String l_strOrderBy,
        String l_strConditions,
        Object[] l_objBindVars)
    {
        this(null, l_strWhere, l_strOrderBy, l_strConditions, l_objBindVars);
    }
    
    public WEB3DefaultPreLoaderParams(
            RowType l_rowType,
            String l_strWhere,
            String l_strOrderBy,
            String l_strConditions,
            Object[] l_objBindVars)
        {
            rowType = l_rowType;
            where = l_strWhere;
            orderBy = l_strOrderBy;
            conditions = l_strConditions;
            bindVars = l_objBindVars;
        }
        
    public static WEB3DefaultPreLoaderParams getDefaultParams()
    {
    	return DEFALUT_PARAMS;
    }
    
    /**
     * @@return
     */
    public RowType getRowType()
    {
        return rowType;
    }

    /**
     * @@return
     */
    public String getWhere()
    {
        return where;
    }

    /**
     * @@return
     */
    public String getOrderBy()
    {
        return orderBy;
    }

    /**
     * @@return
     */
    public String getConditions()
    {
        return conditions;
    }

    /**
     * @@return
     */
    public Object[] getBindVars()
    {
        return bindVars;
    }

    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
    	StringBuffer l_sb = new StringBuffer();
    	l_sb.append("{rowType=").append(rowType);
    	l_sb.append(",where=").append(where);
    	l_sb.append(",orderBy=").append(orderBy);
    	l_sb.append(",conditions=").append(conditions);
    	l_sb.append(",bindVars=");
    	if (bindVars != null && bindVars.length > 0)
    	{
			l_sb.append("[");
    		for (int i = 0; i < bindVars.length; i++)
    		{
    			if (i > 0)
    			{
    				l_sb.append(",");
    			}
    			l_sb.append(bindVars[i]);
    		}
			l_sb.append("]");
    	} else {
    		l_sb.append(bindVars);
    	}
    	l_sb.append("}");
        return l_sb.toString();
    }

}
@
