head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F���̓��X�|���X(WEB3AdminAioCashinConfirmInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����A���m�F���̓��X�|���X)<BR>
 * �����A���m�F���̓��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinConfirmInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_confirm_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101615L;        
    
    /**
     * (���X�R�[�h)<BR>
     * ��ʕ\���̃f�t�H���g�ƂȂ镔�X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�A�������i���j)<BR>
     * ��ʕ\���̃f�t�H���g�ƂȂ�A�������i���j<BR>
     */
    public Date minNoticeDate;
    
    /**
     * (�A�������i��)<BR>
     * ��ʕ\���̃f�t�H���g�ƂȂ�A�������i���j<BR>
     */
    public Date maxNoticeDate;
    
    /**
     * (�U������Z�@@�ֈꗗ)<BR>
     * �Y������،���Ђ��g�p���Ă�����Z�@@�ւ̃��X�g<BR>
     */
    public WEB3AioFinancialInstitutionUnit[] financialInstitutionUnits;
    
    /**
     * @@roseuid 4158EB63003A
     */
    public WEB3AdminAioCashinConfirmInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminAioCashinConfirmInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
