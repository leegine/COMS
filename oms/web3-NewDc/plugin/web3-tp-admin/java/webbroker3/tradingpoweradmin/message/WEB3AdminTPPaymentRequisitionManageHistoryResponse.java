head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ��������X�|���X(WEB3AdminTPPaymentRequisitionManageHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * ���������Ǘ��������X�|���X
 */
public class WEB3AdminTPPaymentRequisitionManageHistoryResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_payment_requisition_manage_history";

    /**
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * �ڋq��
     */
    public String accountName;
    
    /**
     * ���������Ǘ������ꗗ
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit[] historyUnits;
//    public WEB3AdminTPPaymentRequisitionManageHistoryUnit WEB3AdminTPPaymentRequisitionManageHistoryUnit[];
    
    /**
     * @@roseuid 4412A9CA02F4
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryResponse() 
    {
     
    }
}
@
