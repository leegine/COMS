head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionsOrderCarryOverRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������J�z���N�G�X�g(WEB3OptionsOrderCarryOverRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 羉s (���u) �V�K�쐬
Revesion History : 2006/06/21 �Ј��� (���u) �d�l�ύX ���f�� 670
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�����w���I�v�V���������J�z���N�G�X�g)<BR>
 * �����w���I�v�V���������J�z���N�G�X�g�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsOrderCarryOverRequest  extends WEB3IfoOrderCarryOverMainRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderCarryOver";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111045L;
    
    /**
     * @@roseuid 40C0AE5202CE
     */
    public WEB3OptionsOrderCarryOverRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * super.validate()���R�[������B <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3OptionsOrderCarryOverResponse(this);
    }
}
@
