head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBatoUrlResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�d�q��URL�擾���X�|���X(WEB3IPOBatoUrlResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/26 ���g(���u) �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (IPO�d�q��URL�擾���X�|���X)<BR>
 * IPO�d�q��URL�擾���X�|���X�N���X
 * 
 * @@author ���g
 * @@version 1.0
 */
public class WEB3IPOBatoUrlResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_batoUrl";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200601251132L;
    
    /**
     * (�ғ��`�F�b�N����)<BR>
     * �ғ��`�F�b�N����<BR>
     * <BR>
     * true:�ғ��� false:��~��
     */
    public boolean isWorking;
    
    /**
     * (URL)<BR>
     * �`�F�b�N���ʂ��h�{�����ρh�̏ꍇ�A�ژ_�����\���̍ۂɎg�p����URL
     */
    public String url;
    
    /**
     * (�n�b�V���l)<BR>
     * �n�b�V���l
     */
    public String hashValue;
    
    /**
     * @@roseuid 43D8344F006D
     */
    public WEB3IPOBatoUrlResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D26DC4013F
     */
    public WEB3IPOBatoUrlResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }

}
@
