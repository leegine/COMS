head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ��ꗗ���X�|���X�N���X(WEB3AdminTPPaymentRequisitionManageSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * ���������Ǘ��ꗗ���X�|���X
 */
public class WEB3AdminTPPaymentRequisitionManageSearchResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_payment_requisition_manage_List";
    
    /**
     * ���y�[�W��
     */
    public String totalPages;
    
    /**
     * �����R�[�h��
     */
    public String totalRecords;
    
    /**
     * �\���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * ���������ڋq�ꗗ
     */
    public WEB3AdminTPPaymentRequisitionManageUnit[] manageUnits;
    
    /**
     * @@roseuid 4412A9CA017D
     */
    public WEB3AdminTPPaymentRequisitionManageSearchResponse() 
    {
     
    }
}
@
