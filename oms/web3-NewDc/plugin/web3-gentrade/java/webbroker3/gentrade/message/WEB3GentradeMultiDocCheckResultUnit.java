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
filename	WEB3GentradeMultiDocCheckResultUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ژ_�����{���`�F�b�NUnit�N���X(WEB3GentradeMultiDocCheckResultUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/17 ��іQ(���u) �V�K�쐬 ���f��No.330
*/

package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���������ژ_�����{���`�F�b�NUnit�N���X)<BR>
 * ���������ژ_�����{���`�F�b�NUnit�N���X<BR>
 * <BR>
 * @@author ��іQ(���u)
 * @@version 1.0 
 */
public class WEB3GentradeMultiDocCheckResultUnit extends Message
{
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String typeCode;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�`�F�b�N����)<BR>
     * �`�F�b�N����<BR>
     * <BR>
     * 0�F �{����<BR>
     * 1�F �{������<BR>
     */
    public String checkResult;

    /**
     * ���������ژ_�����{���`�F�b�N����Unit�N���X<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3GentradeMultiDocCheckResultUnit() 
    {

    }
}@
