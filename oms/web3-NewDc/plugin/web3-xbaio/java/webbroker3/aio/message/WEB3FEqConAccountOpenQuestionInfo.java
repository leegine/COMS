head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenQuestionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�ݎ�����(WEB3FEqConAccountOpenQuestionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�O�������J�ݎ�����)<BR>
 * �O�������J�ݎ�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenQuestionInfo extends Message 
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
    public String questionContent;
    
    /**
     * (�����)<BR>
     * �����
     */
    public String questionAnswer;
    
    /**
     * (�O�������J�ݎ�����)<BR>
     * �R���X�g���N�^
     * @@roseuid 41CF95DD00C3
     */
    public WEB3FEqConAccountOpenQuestionInfo() 
    {
     
    }
}
@
