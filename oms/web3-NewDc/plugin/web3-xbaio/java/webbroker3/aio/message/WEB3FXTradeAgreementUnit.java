head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTradeAgreementUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX������ӎ�����(WEB3FXTradeAgreementUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
Revesion History : 2008/10/07 ���u�� (���u) �d�l�ύX�E���f��1061
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX������ӎ�����) <BR>
 * FX������ӎ����� <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTradeAgreementUnit extends Message
{
    /**
     * (����ԍ�) <BR>
     * ����ԍ� <BR>
     */
    public String questionNumber;

    /**
     * (������e) <BR>
     * ������e <BR>
     */
    public String questionContents;

    /**
     * (�����) <BR>
     * <BR>
     * 1�F���� <BR>
     * 2�F�񓯈� <BR>
     * A�F��A<BR>
     * B�F��B<BR>
     * C�F��C<BR>
     * D�F��D<BR>
     */
    public String questionAnswer;

    /**
     * (FX������ӎ�����) <BR>
     * �R���X�g���N�^�B <BR>
     * 
     * @@return webbroker3.aio.message.WEB3FXTradeAgreementUnit
     * @@roseuid 41B039BB0108
     */
    public WEB3FXTradeAgreementUnit()
    {
    }
}@
