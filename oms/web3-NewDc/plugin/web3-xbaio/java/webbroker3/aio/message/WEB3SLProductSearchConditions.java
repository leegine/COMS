head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLProductSearchConditions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����������N���X(WEB3SLProductSearchConditions.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����F (���u) �V�K�쐬 ���f��760
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�S�ۖ����������)<BR>
 * �S�ۖ����������<BR>
 * 
 * @@author �����F
 * @@version 1.0
 */
public class WEB3SLProductSearchConditions extends Message
{
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public long productId;

    /**
     * (�K�p����from)<BR>
     * �K�p����from<BR>
     */
    public Date targetPeriodFrom;
    
    /**
     * (�S�ۖ����������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46DBBB800130
     */
    public WEB3SLProductSearchConditions()
    {

    }
}
@
