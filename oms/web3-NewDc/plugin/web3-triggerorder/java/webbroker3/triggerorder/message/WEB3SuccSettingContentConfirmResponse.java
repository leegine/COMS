head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSettingContentConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݒ���e�m�F���X�|���X(WEB3SuccSettingContentConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�ݒ���e�m�F���X�|���X)<BR>
 * �ݒ���e�m�F���X�|���X�N���X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3SuccSettingContentConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_settingContentConfirm";
    
    /**
     * (��������)<BR>
     * �A�������ݒ�Ώۂ̖���<BR>
     */
    public WEB3SuccOrderUnit orderUnit;
    
    /**
     * @@roseuid 434896040251
     */
    public WEB3SuccSettingContentConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccSettingContentConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@