head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������Ǘ��������j�b�g�N���X(WEB3AdminTPPaymentRequisitionManageHistoryUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
 */

package webbroker3.tradingpoweradmin.message;

import java.util.Date;

/**
 * ���������Ǘ��������j�b�g
 */
public class WEB3AdminTPPaymentRequisitionManageHistoryUnit extends WEB3AdminTPPaymentRequisitionManageHistoryResponse 
{
    
    /**
     * ���t
     */
    public Date bizDate;
    
    /**
     * ����
     * 
     * 1:�O�� 2:�ؕ] 3:�M�p
     */
    public String attribute;
    
    /**
     * ��~
     */
    public String[] tradingPowerStop;

    /**
     * 20%���ꔭ����
     */
    public Date break20Day;
    
    /**
     * 20%����o�ߓ���
     */
    public String break20ElapsedDays;
    
    /**
     * 30%���ꔭ����
     */
    public Date break30Day;
    
    /**
     * 30%����o�ߓ���
     */
    public String break30ElapsedDays;
    
    /**
     * ���������Ǘ����׈ꗗ
     */
    public WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails;
//    public WEB3AdminTPPaymentRequisitionManageHistoryResponse WEB3AdminTPPaymentRequisitionManageHistoryResponse;
    
    /**
     * @@roseuid 4412A9CB0083
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit() 
    {
     
    }
}
@
