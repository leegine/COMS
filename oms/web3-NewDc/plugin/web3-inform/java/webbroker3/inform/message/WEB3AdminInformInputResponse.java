head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍����̓��X�|���X�N���X(WEB3AdminInformInputResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�A����񌟍����̓��X�|���X)<BR>
 * �A����񌟍����̓��X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (�O�c�Ɠ�)<BR>
     * �O�c�Ɠ����t
     */
    public Date previousBizDate;
    
    /**
     * (����)<BR>
     * �������t
     */
    public Date previousDate;
    
    /**
     * @@roseuid 41EE625B02FD
     */
    public WEB3AdminInformInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminInformInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
