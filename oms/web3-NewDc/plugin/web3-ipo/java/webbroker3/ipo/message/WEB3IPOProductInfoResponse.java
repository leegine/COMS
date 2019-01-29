head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOProductInfoResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�ʖ�����񃌃X�|���X�N���X(WEB3IPOProductInfoResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�ʖ�����񃌃X�|���X�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOProductInfoResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_productInfo";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171834L;
    
    /**
     * �\���\�t���O<BR>
     * <BR>
     * true�F�@@�\���\<BR>
     * false�F�@@�\���s��<BR>
     */
    public boolean demandFlag;
    
    /**
     * (�������)
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112E79C00A7
     */
    public WEB3IPOProductInfoResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40D1670C0111
     */
    public WEB3IPOProductInfoResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
