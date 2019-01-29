head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRegisterConOrdersMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 条件付注文注文登録コンテキスト情報クラス(WEB3RlsRegisterConOrdersMessageContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ劉　@新規作成
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * 条件付注文注文登録コンテキスト情報クラス
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3RlsRegisterConOrdersMessageContext
    extends WEB3RlsMessageContext
{

    /**
     * 親注文商品タイプ
     */
    private ProductTypeEnum conOrderProductType;

    /**
     * 親注文商品ID
     */
    private Long conOrderId;

    /**
     * 子注文商品タイプリスト
     */
    private ProductTypeEnum[] subOrderProductTypes;

    /**
     * 子注文IDプリスト
     */
    private Long[] subOrderIds;

    /**
     * 親注文商品タイプを取得する
     */
    public ProductTypeEnum getConOrderProductType()
    {
        return conOrderProductType;
    }

    /**
     * 親注文商品タイプを設定する
     */
    public void setConOrderProductType(ProductTypeEnum l_conOrderProductType)
    {
        conOrderProductType = l_conOrderProductType;
    }

    /**
     * 親注文商品IDを取得する
     */
    public Long getConOrderId()
    {
        return conOrderId;
    }

    /**
     * 親注文商品IDを設定する
     */
    public void setConOrderId(Long l_lngConOrderId)
    {
        conOrderId = l_lngConOrderId;
    }

    /**
     * 子注文IDプリストを取得する
     */
    public Long[] getSubOrderIds()
    {
        return subOrderIds;
    }

    /**
     * 子注文IDプリストを設定する
     */
    public void setSubOrderIds(Long[] l_lngSubOrderIds)
    {
        subOrderIds = l_lngSubOrderIds;
    }

    /**
     * 子注文商品タイプリストを取得する
     */
    public ProductTypeEnum[] getSubOrderProductTypes()
    {
        return subOrderProductTypes;
    }

    /**
     * 子注文商品タイプリストを設定する
     */
    public void setSubOrderProductTypes(ProductTypeEnum[] l_subOrderProductTypes)
    {
        subOrderProductTypes = l_subOrderProductTypes;
    }

    /**
     * WEB3RlsRegisterConOrdersMessageContext
     */
    public WEB3RlsRegisterConOrdersMessageContext(SubAccount l_subaccount,
                                                  int l_intOrderType,
                                                  ProductTypeEnum l_conOrderProductType,
                                                  Long l_lngConOrderId,
                                                  ProductTypeEnum[]
                                                  l_subOrderProductTypes,
                                                  Long[] l_lngSubOrderIds)
    {
        super(l_subaccount, l_intOrderType);
        conOrderProductType = l_conOrderProductType;
        conOrderId = l_lngConOrderId;
        subOrderIds = l_lngSubOrderIds;
        subOrderProductTypes = l_subOrderProductTypes;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{[WEB3RlsRegisterConOrdersMessageContext]>>>");
        l_sb.append("subAccount=").append(this.getSubAccount().getAccountId())
            .append(";")
            .append(this.getSubAccount().getSubAccountId());
        l_sb.append(",conOrderType=").append(this.getConOrderType());
        l_sb.append(",conOrderProductType=").append(this.getConOrderProductType());
        l_sb.append(",conOrderId=").append(this.getConOrderId());
        l_sb.append(",subOrders=(");
        if (subOrderIds != null)
        {
            for (int i = 0; i < subOrderIds.length; i++)
            {
                l_sb.append(" subOrderId=").append(subOrderIds[i]);
                l_sb.append(" ,subOrderProductType=").append(subOrderProductTypes[i]);
            }
        }
        l_sb.append(")");
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
