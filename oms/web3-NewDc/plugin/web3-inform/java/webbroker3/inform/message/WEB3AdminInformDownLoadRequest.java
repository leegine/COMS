head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformDownLoadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��_�E�����[�h���N�G�X�g�N���X(WEB3AdminInformDownLoadRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�A����񌟍��_�E�����[�h���N�G�X�g)<BR>
 * �A����񌟍��_�E�����[�h���N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformDownLoadRequest extends WEB3AdminInformCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;
    
    /**
     * @@roseuid 41EE625B01C5
     */
    public WEB3AdminInformDownLoadRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformDownLoadResponse(this);
    }
}
@
