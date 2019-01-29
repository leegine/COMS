head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.25.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsManualSubmitConOrderMessageContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 強制発注条件付注文登録コンテキスト情報クラス(WEB3RlsForceSubmitConOrdersMessageContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2003/09/14 FLJ劉　@新規作成
 */
package webbroker3.rlsgateway;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * 強制発注条件付注文登録コンテキスト情報クラス
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3RlsManualSubmitConOrderMessageContext
    extends WEB3RlsMessageContext
{

    /**
     *注文商品タイプ
     */
    private ProductTypeEnum conOrderProductType;

    /**
     * 注文商品ID
     */
    private Long conOrderId;

    /**
     * 親注文商品タイプ
     */
    private ProductTypeEnum parentOrderProductType;

    /**
     * 親注文ID　@
     */
    private Long parentOrderId;

    /**
     * 注文発注順番
     */
    private int serialNoInParent;

    /**
     * 発注者ログインID
     */
    private Long submitterLoginId;

    /**
     * 通知経路
     */
    private String submitNotifyType;

    /**
     * 注文商品タイプを取得する
     */
    public ProductTypeEnum getConOrderProductType()
    {
        return conOrderProductType;
    }

    /**
     *注文商品タイプを設定する
     */
    public void setConOrderProductType(ProductTypeEnum l_conOrderProductType)
    {
        conOrderProductType = l_conOrderProductType;
    }

    /**
     * 注文商品IDを取得する
     */
    public Long getConOrderId()
    {
        return conOrderId;
    }

    /**
     * 注文商品IDを設定する
     */
    public void setConOrderId(Long l_lngConOrderId)
    {
        conOrderId = l_lngConOrderId;
    }

    /**
     * 親注文IDを取得する
     */
    public Long getParentOrderId()
    {
        return parentOrderId;
    }

    /**
     * 親注文IDを設定する
     */
    public void setParentOrderId(Long l_lngParentOrderId)
    {
        parentOrderId = l_lngParentOrderId;
    }

    /**
     * 子注文商品タイプを取得する
     */
    public ProductTypeEnum getParentOrderProductType()
    {
        return parentOrderProductType;
    }

    /**
     * 注文発注順番を取得する
     */
    public int getSerialNoInParent()
    {
        return serialNoInParent;
    }

    /**
     * 発注者ログインIDを取得する
     */
    public Long getSubmitterLoginId()
    {
        return submitterLoginId;
    }

    /**
     * 通知経路を取得する
     */
    public String getSubmitNotifyType()
    {
        return submitNotifyType;
    }

    /**
     * 子注文商品タイプを設定する
     */
    public void setParentOrderProductType(ProductTypeEnum l_parentOrderProductType)
    {
        parentOrderProductType = l_parentOrderProductType;
    }

    /**
     * 通知経路を設定する
     */
    public void setSerialNoInParent(int l_intSerialNoInParent)
    {
        this.serialNoInParent = l_intSerialNoInParent;
    }

    /**
     * 発注者ログインIDを設定する
     */
    public void setSubmitterLoginId(Long submitterLoginId)
    {
        this.submitterLoginId = submitterLoginId;
    }

    /**
     * 注文発注順番を設定する
     */
    public void setSubmitNotifyType(String submitNotifyType)
    {
        this.submitNotifyType = submitNotifyType;
    }

    /**
     * WEB3RlsRegisterConOrdersMessageContext
     */
    public WEB3RlsManualSubmitConOrderMessageContext(
        Long l_lngSubmitterLoginId,
        String l_strSubmitNotifyType,
        SubAccount l_subaccount,
        int l_intOrderType,
        ProductTypeEnum l_conOrderProductType,
        Long l_lngConOrderId,
        ProductTypeEnum
        l_parentOrderProductType,
        Long l_lngParentOrderId,
        int l_intSerialNoInParent)
    {
        super(l_subaccount, l_intOrderType);
        conOrderProductType = l_conOrderProductType;
        conOrderId = l_lngConOrderId;
        parentOrderId = l_lngParentOrderId;
        parentOrderProductType = l_parentOrderProductType;
        serialNoInParent = l_intSerialNoInParent;
        this.submitterLoginId = l_lngSubmitterLoginId;
        this.submitNotifyType = l_strSubmitNotifyType;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("{[WEB3RlsManualSubmitConOrderMessageContext]>>>");
        l_sb.append("subAccount=").append(this.getSubAccount().getAccountId())
            .append(";")
            .append(this.getSubAccount().getSubAccountId());
        l_sb.append(",conOrderType=").append(this.getConOrderType());
        l_sb.append(",conOrderProductType=").append(this.getConOrderProductType());
        l_sb.append(",conOrderId=").append(this.getConOrderId());
        l_sb.append(",parentOrderProductType=").append(this.getParentOrderProductType());
        l_sb.append(",parentOrderId=").append(this.getParentOrderId());
        l_sb.append(",serialNoInParent=").append(this.getSerialNoInParent());
        l_sb.append(",submitterLoginId=").append(this.getSubmitterLoginId());
        l_sb.append(",submitNotifyType=").append(this.getSubmitNotifyType());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
