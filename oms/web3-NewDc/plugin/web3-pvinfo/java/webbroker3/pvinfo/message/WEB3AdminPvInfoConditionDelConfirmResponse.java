head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�\���ݒ�폜�m�F���X�|���X(WEB3AdminPvInfoConditionDelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�\���ݒ�폜�m�F���X�|���X)<BR>
 * �Ǘ��ҁE�\���ݒ�폜�m�F���X�|���X�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDelConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionDelConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\�����e���)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit displayContentsUnit;
    
    /**
     * (�Ώیڋq���ꗗ)<BR>
     */
    public WEB3PvInfoAccountInformationUnit[] accountInformationUnits;
    
    /**
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */  
    public WEB3AdminPvInfoConditionDelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);    
    }  
}
@
