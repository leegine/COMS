head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϘA�g���|�[�g�ꗗ���X�|���X�N���X(WEB3AdminAioSettleReportListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (���ϘA�g���|�[�g�ꗗ���X�|���X)<BR>
 * ���ϘA�g���|�[�g�ꗗ���X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioSettleReportListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_settle_report_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121138L;           
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (���ϋ@@��ID)<BR>
     * ��ʂɂđI�����ꂽ���ϋ@@��ID
     */
    public String paySchemeId;
    
    /**
     * (���ϋ@@�֖�)<BR>
     * ��ʂɂđI�����ꂽ���ϋ@@�ւ̖���
     */
    public String paySchemeName;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��
     */
    public Date deliveryDate;
    
    /**
     * (�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�������i���j
     */
    public Date minOrtderDate;
    
    /**
     * (�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�������i���j
     */
    public Date maxOrtderDate;
    
    /**
     * (�������)<BR>
     * ��ʂɂđI�����ꂽ�������
     */
    public String transactionStatus;
    
    /**
     * (.com�f�r�b�g���ώ���ԍ��i���j)<BR>
     * ��ʂɂē��͂��ꂽ.com�f�r�b�g���ώ���ԍ��i���j
     */
    public String minComDebitNumber;
    
    /**
     * (.com�f�r�b�g���ώ���ԍ��i���j)<BR>
     * ��ʂɂē��͂��ꂽ.com�f�r�b�g���ώ���ԍ��i���j
     */
    public String maxComDebitNumber;
    
    /**
     * (���ϘA�g���|�[�g����)<BR>
     * ���ϘA�g���|�[�g�̖��׍s�̃��X�g
     */
    public WEB3AioSettleReportUnit[] settleReportUnits;
    
    /**
     * (�\���y�[�W�ԍ�)
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)
     */
    public String totalRecords;   
    
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminAioSettleReportListResponse(WEB3AdminAioSettleReportListRequest l_request) 
    {
        super(l_request);
    }
    
}
@
