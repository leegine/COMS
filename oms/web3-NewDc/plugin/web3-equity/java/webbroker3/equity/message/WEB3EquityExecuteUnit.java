head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������Ɖ���(WEB3EquityExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 �����F (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i���������������Ɖ���j�B<BR>
 * <BR>
 * ���������������Ɖ� ���@@�f�[�^�N���X<BR>
 * <BR>
 * ���������F�u�������Ɖ�v<BR>
 * ���������F�u��������ꗗ�v�̗���ʂŎg�p����B
 * @@version 1.0
 */
public class WEB3EquityExecuteUnit extends Message
{

    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (��芔��)<BR>
     * �������ɕR�t�������̐���<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     * �������ɕR�t�������̒P��<BR>
     */
    public String execPrice;

    /**
     * (���������������Ɖ���)<BR>
     * �R���X�g���N�^
     * @@roseuid 407CC7ED0046
     */
    public WEB3EquityExecuteUnit()
    {

    }
}
@
