head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������R�[�h����(WEB3EquityProductCodeNameUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 �����F (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�������������R�[�h���́j�B<BR>
 * <BR>
 * �����ꗗ�p�N���X
 * @@version 1.0
 */
public class WEB3EquityProductCodeNameUnit extends Message
{

    /**
     * (�����R�[�h)<BR>
     * �������ɑΉ����������R�[�h<BR>
     */
    public String productCode;

    /**
     * (������)<BR>
     * �����R�[�h�ɑΉ�����������<BR>
     */
    public String productName;

    /**
     * (�����R�[�h����)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4073BCCD0389
     */
    public WEB3EquityProductCodeNameUnit()
    {

    }
}
@
