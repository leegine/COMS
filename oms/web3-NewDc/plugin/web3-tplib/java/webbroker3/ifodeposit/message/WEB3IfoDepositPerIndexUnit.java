head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositPerIndexUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �w���ʏ؋����N���X(WEB3IfoDepositPerIndexUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/03 �L�R�@@�ˎq(SRA) �V�K�쐬
 */
package webbroker3.ifodeposit.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�w���ʏ؋���)<BR>
 * �w���ʏ؋����N���X�B<BR>
 * 
 * @@author Shoko Ariyama (SRA)
 */
public class WEB3IfoDepositPerIndexUnit extends Message
{

    /**
     * (�����Y�����R�[�h)<BR>
     * �����Y�����R�[�h�B<BR>
     */
    public String targetProductCode;

    /**
     * (�K��؋���)<BR>
     * �K��؋����B<BR>
     */
    public String regIfoDeposit;

    /**
     * �R���X�g���N�^�B
     */
    public WEB3IfoDepositPerIndexUnit()
    {

    }
}
@
