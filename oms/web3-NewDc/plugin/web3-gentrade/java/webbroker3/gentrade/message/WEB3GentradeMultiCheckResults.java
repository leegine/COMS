head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMultiCheckResults.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ژ_�����{���`�F�b�N����(WEB3GentradeMultiCheckResults.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/17 ��іQ(���u) �V�K�쐬 ���f��No.330
*/

package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���������ژ_�����{���`�F�b�N����)<BR>
 * ���������ژ_�����{���`�F�b�N����<BR>
 * <BR>
 * @@author ��іQ(���u)
 * @@version 1.0 
 */
public class WEB3GentradeMultiCheckResults extends Message
{
    /**
     * (�`�F�b�N����)<BR>
     * �`�F�b�N����<BR>
     */
    public WEB3GentradeMultiDocCheckResultUnit[] checkResult;

    /**
     * �ژ_�����\���̍ۂɎg�p����URL<BR>
     */
    public String url;

    /**
     * (�n�b�V���l)<BR>
     * �n�b�V���l<BR>
     */
    public String hashValue;

    /**
     * (��Q���t���O)<BR>
     * ��Q���t���O<BR>
     * <BR>
     * false�F�ғ���<BR>
     * true�F��Q��<BR>
     */
    public boolean batoFailureFlag;

    /**
     * ���������ژ_�����{���`�F�b�N����<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3GentradeMultiCheckResults() 
    {

    }
}@
