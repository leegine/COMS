head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�\���ݒ�폜�������X�|���X(WEB3AdminPvInfoConditionDelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�\���ݒ�폜�������X�|���X)<BR>
 * �Ǘ��ҁE�\���ݒ�폜�������X�|���X�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionDelComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;

    /**
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminPvInfoConditionDelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
