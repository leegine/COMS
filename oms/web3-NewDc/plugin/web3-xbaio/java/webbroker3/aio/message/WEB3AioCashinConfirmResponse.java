head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����z�m�F���X�|���X(WEB3AioCashinConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬            
                   2004/10/22 ���� (���u) ���r���[     
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (�����z�m�F���X�|���X)<BR>
 * �����z�m�F���X�|���X�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinConfirmResponse extends WEB3AioCashinCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;     
    
    /**
     * (�������z)<BR>
     * ����������悤�Ƃ��Ă�������z<BR>
     */
    public String cashinAmt;
    
    /**
     * (������)<BR>
     * ����̓������܂߂�������<BR>
     */
    public String cashinTimes;
    
    /**
     * (�������v���z)<BR>
     * ����̓������܂߂��������v���z<BR>
     */
    public String cashinTotalAmt;
    
    /**
     * (�m�F��������)<BR>
     * �m�F�������̔�����<BR>
     * �i��ʕ\���Ȃ��j<BR>
     * <BR>
     */
    public Date checkDate;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4158E9B70052
     */
    public WEB3AioCashinConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashinConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
