head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplySearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\���������̓��X�|���X(WEB3AdminAccOpenApplySearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݐ\���������̓��X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݐ\���������̓��X�|���X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplySearchInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_applySearchInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081619L;

    /**
     * (�O�c�Ɠ�)<BR>
     * �O�c�Ɠ�<BR>
     */
    public Date previousBizDate;

    /**
     * (�O��)<BR>
     * �O��<BR>
     */
    public Date previousDate;

    /**
     * @@roseuid 41B45E7B03B9
     */
    public WEB3AdminAccOpenApplySearchInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenApplySearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
