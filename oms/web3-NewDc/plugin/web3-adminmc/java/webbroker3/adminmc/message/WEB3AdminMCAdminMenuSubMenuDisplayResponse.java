head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.50.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminMenuSubMenuDisplayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����T�u���j���[�\�����X�|���X(WEB3AdminMCAdminMenuSubMenuDisplayResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����T�u���j���[�\�����X�|���X)<BR>
 * �Ǘ��҃��j���[����T�u���j���[�\�����X�|���X<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminMenuSubMenuDisplayResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminMenuSubMenuDisplay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (�����\�@@�\�J�e�S���ꗗ)<BR>
     * �����\�@@�\�J�e�S���ꗗ<BR>
     */
    public WEB3AdminMCTransactionCategoryUnit[] transactionCategoryUnits;
    
    /**
     * (�Ǘ��҃��j���[����T�u���j���[�\�����X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse
     * @@roseuid 4175DF8700CD
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
