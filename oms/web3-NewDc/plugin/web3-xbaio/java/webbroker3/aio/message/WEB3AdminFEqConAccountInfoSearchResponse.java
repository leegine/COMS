head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountInfoSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������񌟍����X�|���X(WEB3AdminFEqConAccountInfoSearchResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
                   2006/02/08 ����(���u) �d�l�ύX�E���f��481
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O��������񌟍����X�|���X)<BR>
 * �O��������񌟍����X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountInfoSearchResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_info_search";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171811L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋqID)<BR>
     * �O�������ڋqID
     */
    public String accountId;
    
    /**
     * (�O�������ԍ�)<BR>
     * �O�����������ԍ�
     */
    public String fstkAccountCode;
    
    /**
     * (���O�i���j)<BR>
     * ���O�i���j
     */
    public String familyName;
    
    /**
     * (���O�i���j)<BR>
     * ���O�i���j
     */
    public String name;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X
     */
    public String mailAddress;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�����J�ݏ󋵋敪)<BR>
     * �O�����������󋵋敪<BR>
     * <BR>
     * 1�F �J�ݍ�<BR>
     * 9�F���� <BR>
     * 99:��������
     */
    public String accountOpenStatusDiv;
    
    /**
     * @@roseuid 423554FE0157
     */
    public WEB3AdminFEqConAccountInfoSearchResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFEqConAccountInfoSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
