head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����ڍ׃��X�|���X(WEB3AdminIPOProductDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���o�� �V�K�쐬
Revesion History : 2005/12/20 杊��] (���u) �d�l�ύXNo.101�C��
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ���IPO�����ڍ׃��X�|���X)<BR>
 * IPO�����ڍ׃��X�|���X�N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIPOProductDetailsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productDetails";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161039L;
    
    /**
     * ����R�[�h�ꗗ<BR>
     * <BR>
     * 01�F�@@����<BR>
     * 02�F�@@�폜<BR>
     * 11�F�@@�u�b�N�r���f�B���O�󋵃_�E�����[�h<BR>
     * 12�F�@@���I���ʁ^�w���\���󋵃_�E�����[�h<BR>
     * 21�F�@@���I���ʃA�b�v���[�h<BR>
     * 31�F�@@IPO��W��~�^�ĊJ<BR>
     * 32�F�@@IPO���~<BR>
     * 41�F�@@���I��������<BR>
     * 42�F�@@���I�������ʊm�F<BR>
     * 43�F�@@���I�������ʊ���<BR>
     * <BR>
     */
    public String[] controlCodeList;
    
    /**
     * �������
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112DF8D02D1
     */
    public WEB3AdminIPOProductDetailsResponse() 
    {
     
    }
    
    /**
     * (�Ǘ���IPO�����ڍ׃��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D141F30025
     */
    public WEB3AdminIPOProductDetailsResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
