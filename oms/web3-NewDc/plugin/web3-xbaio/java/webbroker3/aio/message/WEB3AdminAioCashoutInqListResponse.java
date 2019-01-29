head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���ꗗ���X�|���X�N���X(WEB3AdminAioCashoutInqListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2004/12/10 ���E (���u) �c�Ή�
                   2006/07/31 ����� (���u) ����̍X ���f��604
                   2006/09/04 �Ԑi (���u) ����̍X ���f��No.629  
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o���\���⍇���ꗗ���X�|���X)<BR>
 * �o���\���⍇���ꗗ���X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131332L;   
        
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h
     */
    public String[] branchCode;
    
    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��
     */
    public Date deliveryDate;
    
    /**
     * (������t�敪)<BR>
     * ��ʂɂđI�����ꂽ������t�敪<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t��<BR>
     * 2�F ��t�G���[<BR>
     * 3�F �S��<BR>
     * <BR>
     */
    public String orderDiv;
    
    //===========remain zhou-yong NO.1 begin ========
    
    /**
     * (�U����敪)<BR>
     * ��ʂɂđI�����ꂽ�U����敪<BR>
     * <BR>
     * 0�F �h�S�āh<BR> 
     * 1�F �h�X���h<BR> 
     * 2�F �h���̑��h�i�X���ȊO�j 
     */
    public String transferDiv;

    /**
     * (�o���\���⍇������)<BR>
     *  �o���\���⍇������
     */
    public WEB3AioCashoutInqUnit[] cashoutInqUnits;
    
    //===========remain zhou-yong NO.1 end ========  

                          
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
     * (�Ǘ��ҏ����t���O)<BR>
     * ��ʂɏo���A����{�^���A�`�F�b�N�{�b�N�X��\�����邩�ǂ����̋敪<BR>
     * <BR>
     * 0�F �ꗗ�\���̂�<BR>
     * 1�F �o�����{<BR>
     * 2�F ������{<BR>
     * 3�F �������{<BR>
     */
    public String adminProcessingDiv;
    
    /**
     * (���͋敪)<BR>
     * <BR>
     * 0�F�S��<BR>
     * 1�F�ڋq<BR>
     * 2�FSONAR<BR>
     */
    public String inputDiv;  

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminAioCashoutInqListResponse(WEB3AdminAioCashoutInqListRequest l_request) 
    {
        super(l_request);
    }
}
@
