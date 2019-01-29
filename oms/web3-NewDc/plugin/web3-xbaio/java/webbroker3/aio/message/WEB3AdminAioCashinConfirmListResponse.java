head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F�ꗗ���X�|���X(WEB3AdminAioCashinConfirmListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����A���m�F�ꗗ���X�|���X)<BR>
 * �����A���m�F�ꗗ���X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinConfirmListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_confirm_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101830L;    
        
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * ��ʂɂē��͂��ꂽ�����ԍ��i�ڋq�R�[�h�j�i���j
     */
    public String minAccountCode;
    
    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * ��ʂɂē��͂��ꂽ�����ԍ��i�ڋq�R�[�h�j�i���j<BR>
     */
    public String maxAccountCode;
    
    /**
     * (�A�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�A�������i���j<BR>
     */
    public Date minNoticeDate;
    
    /**
     * (�A�������i���j)<BR>
     * ��ʂɂē��͂��ꂽ�A�������i���j<BR>
     */
    public Date maxNoticeDate;
    
    /**
     * (�U�����i���j)<BR>
     * ��ʂɂē��͂��ꂽ�U�����i���j<BR>
     */
    public Date minTransferDate;
    
    /**
     * (�U�����i���j)<BR>
     * ��ʂɂē��͂��ꂽ�U�����i���j<BR>
     */
    public Date maxTransferDate;
    
    /**
     * (�U������Z�@@�փR�[�h)<BR>
     * ��ʂɂđI�����ꂽ���Z�@@�փR�[�h<BR>
     * <BR>
     * �u���ׂāv���I�����ꂽ�ꍇ�́Anull<BR>
     */
    public String finInstitutionCode;
    
    /**
     * (�U������Z�@@�֖�)<BR>
     * ��ʂɂđI�����ꂽ���Z�@@�փR�[�h�ɑΉ����閼��
     */
    public String finInstitutionName;
    
    /**
     * (����)<BR>
     * �����A���̌���<BR>
     */
    public String outputNumber;
    
    /**
     * (�����A�����׈ꗗ)<BR>
     * �����A�����ׂ̃��X�g<BR>
     */
    public WEB3AioCashinNoticeUnit[] cashinNoticeUnits;
    
    /**
     * (�o�͋敪)<BR>
     * ��ʂɂđI�����ꂽ�o�͋敪<BR>
     * <BR>
     * 0�F �ꗗ<BR>
     * 1�F CSV<BR>
     */
    public String outputDiv;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;
    
    /**
     * @@roseuid 4158EB630365
     */
    public WEB3AdminAioCashinConfirmListResponse() 
    {
     
    }
     
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminAioCashinConfirmListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
