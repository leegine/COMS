head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�_�C���N�g�w��o�^�m�F���X�|���X(WEB3AdminPvInfoDirectRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/27 ������(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�_�C���N�g�w��o�^�m�F���X�|���X)<BR>
 * �Ǘ��ҁE�_�C���N�g�w��o�^�m�F���X�|���X�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectRegistConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_directRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\�����eID)<BR>
     */
    public String displayContentsId;
    
    /**
     * (�A�b�v���[�hID)<BR>
     */
    public String uploadId;
    
    /**
     * (�A�b�v���[�h����)<BR>
     */
    public String uploadNumber;
    
    /**
     * 
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminPvInfoDirectRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@