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
filename	WEB3EquityMarginOrderIdUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p����ID(WEB3EquityMarginOrderIdUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �֔�(���u) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����E�M�p����ID)<BR>
 * �����E�M�p����ID�N���X<BR>
 * @@author  �֔�
 * @@version 1.0
 */
public class WEB3EquityMarginOrderIdUnit extends Message
{

    /**
     * (ID)<BR>
     * ����ID<BR>
     */
    public String id;

    /**
     * (���i�敪)<BR>
     * 1�F ��������<BR>
     * 2�F �M�p���<BR>
     */
    public String productDiv;

    /**
     * (�����E�M�p����ID)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 455D1E7A00CD
     */
    public WEB3EquityMarginOrderIdUnit()
    {

    }
}
@
