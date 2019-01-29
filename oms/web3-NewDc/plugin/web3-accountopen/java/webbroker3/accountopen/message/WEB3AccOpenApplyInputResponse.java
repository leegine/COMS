head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݐ\�����̓��X�|���X(WEB3AccOpenApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
Revesion History : 2009/08/10 �����F(���u) �d�l�ύX ���f��162
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����J�ݐ\�����̓��X�|���X)<BR>
 * �����J�ݐ\�����̓��X�|���X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenApplyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081617L;

    /**
     * (��)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountFamilyName;

    /**
     * (��)<BR>
     * �ڋq���i�����j<BR>
     */
    public String accountName;

    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;

    /**
     * (����ƈ��҃R�[�h)<BR>
     * ����ƈ��҃R�[�h<BR>
     */
    public String brokerageCode;

    /**
     * (�����敪)<BR>
     * �����敪 <BR>
     * <BR>
     * 0�F�l�A�J�E���g <BR>
     * 1�F�@@�l�A�J�E���g<BR>
     */
    public String accountType;

    /**
     * (�����N�����ʃR�[�h)<BR>
     * �����N�����ʃR�[�h<BR>
     */
    public String linkCode;

    /**
     * @@roseuid 41B45E7C0128
     */
    public WEB3AccOpenApplyInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AccOpenApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
