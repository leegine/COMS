head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSTradeAgreementUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS������ӎ�����(WEB3InformPTSTradeAgreementUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.123
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (PTS������ӎ�����)<BR>
 * PTS������ӎ�����
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSTradeAgreementUnit extends Message
{
    /**
     * (����ԍ�)<BR>
     * ����ԍ�
     */
    public String questionNumber;

    /**
     * (������e)<BR>
     * ������e
     */
    public String questionContents;

    /**
     * (�����)<BR>
     * �����<BR>
     * <BR>
     * 1�F�͂�<BR>
     * 2�F������
     */
    public String questionAnswer;

    /**
     * �R���X�g���N�^�B
     * @@roseuid 47B9271A02AF
     */
    public WEB3InformPTSTradeAgreementUnit()
    {

    }
}
@
