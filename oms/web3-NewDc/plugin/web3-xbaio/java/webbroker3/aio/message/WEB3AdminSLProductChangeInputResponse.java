head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�ύX���̓��X�|���X(WEB3AdminSLProductChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �g�E�N�|(���u) �V�K�쐬 �d�l�ύX���f��760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�S�ۖ����o�^�ύX���̓��X�|���X)<BR>
 * �S�ۖ����o�^�ύX���̓��X�|���X�N���X<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3AdminSLProductChangeInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141045L;

    /**
     * (�����o�^���)<BR>
     * �S�ۖ����o�^���I�u�W�F�N�g<BR>
     */
    public WEB3SLProductInfoUnit stockLoanProductInfo;

    /**
     * @@roseuid 46E8908403E4
     */
    public WEB3AdminSLProductChangeInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminSLProductChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@