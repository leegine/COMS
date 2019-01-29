head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����������̓��X�|���X(WEB3AdminIPOProductChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �d�� (���u) �V�K�쐬
Revesion History : 2010/09/23 �Ԑi (���u) ���f�� No.181
*/
package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����������̓��X�|���X)<BR>
 * �Ǘ���IPO�����������̓��X�|���X�f�[�^�N���X
 * 
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004081610340L;
    
    /**
     * ���J�s��R�[�h�ꗗ<BR>
     * <BR>
     * ���J�s��R�[�h<BR>
     * <BR>
     * 10�F�@@���؁@@<BR>
     * 11�F�@@���؈ꕔ�@@<BR>
     * 12�F�@@���ؓ񕔁@@<BR>
     * 13�F�@@�}�U�[�Y�@@<BR>
     * 20�F�@@��؁@@<BR>
     * 21�F�@@��؈ꕔ�@@<BR>
     * 22�F�@@��ؓ񕔁@@<BR>
     * 30�F�@@���؁@@<BR>
     * 31�F�@@���؈ꕔ�@@<BR>
     * 32�F�@@���ؓ񕔁@@<BR>
     * 33�F�@@�Z���g���b�N�X<BR>
     * 40�F�@@���؁@@<BR>
     * 41�F�@@Q-Board<BR>
     * 50�F�@@�D�؁@@<BR>
     * 51�F�@@�A���r�V���X<BR>
     * 90�F�@@JASDAQ�i�X�^���_�[�h�j
     * 91�F�@@JASDAQ�i�O���[�X�j
     * <BR>
     */
    public String[] publicOfferingMarketList;
    
    /**
     * ���ݓ���
     */
    public Date currentDate;
    
    /**
     * (�������)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112E33A003F
     */
    public WEB3AdminIPOProductChangeInputResponse() 
    {
     
    }
    
    /**
     * (�Ǘ���IPO�����������̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D1424C03BF
     */
    public WEB3AdminIPOProductChangeInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
     
    }
}
@
