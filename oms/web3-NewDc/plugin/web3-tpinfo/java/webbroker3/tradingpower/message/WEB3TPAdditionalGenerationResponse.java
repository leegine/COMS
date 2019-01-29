head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdditionalGenerationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǐؔ�����ʕ\�����X�|���X(WEB3TPAdditionalGenerationResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/10 �����i���u�j�V�K�쐬 ���f��No.312
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǐؔ�����ʕ\�����X�|���X) <BR>
 * �Ǐؔ�����ʕ\�����X�|���X�N���X�B<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3TPAdditionalGenerationResponse extends WEB3GenResponse
{

    /**
     * (PTYPE) <BR>
     */
    public static final String PTYPE = "additional_generation";

    /**
     * (SerialVersionUID) <BR>
     */
    public static final long serialVersionUID = 200810101032L;

    /**
     * (�Ǐؔ�����)<BR>
     * �Ǐؔ�����<BR>
     * <BR>
     * 0 : �Ǐؖ�����<BR>
     * 1 : ��ꐅ���Ǐؔ���<BR>
     * 2 : ��񐅏��Ǐؔ���<BR>
     */
    public String additionalGenerationStateDiv;

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * �ۏ؋������U�֌㔻��t���O<BR>
     * <BR>
     * false : �ۏ؋������U�֑O<BR>
     * true : �ۏ؋������U�֌�<BR>
     */
    public boolean autoTransferAfterJudgeFlag;

    /**
     * (�Ǐؖ����������t���O)<BR>
     * �Ǐؖ����������t���O<BR>
     * <BR>
     * false : �Ǐؖ���������<BR>
     * true : �Ǐؖ������L��<BR>
     */
    public boolean additionalNonPayAmtFlag;

    /**
     * (��ꐅ���Ǐ؏��)<BR>
     * ��ꐅ���Ǐ؏��<BR>
     */
    public WEB3TPFirstAdditionalInfo firstAdditionalInfo;

    /**
     * (��񐅏��Ǐ؏��)<BR>
     * ��񐅏��Ǐ؏��<BR>
     */
    public WEB3TPSecondAdditionalInfo secondAdditionalInfo;

    /**
     * (�R���X�g���N�^) <BR>
     */
    public WEB3TPAdditionalGenerationResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3TPAdditionalGenerationResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
