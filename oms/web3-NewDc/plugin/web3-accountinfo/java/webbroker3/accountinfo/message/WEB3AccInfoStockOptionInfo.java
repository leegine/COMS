head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStockOptionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X�g�b�N�I�v�V�����������(WEB3AccInfoStockOptionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 �Ԑi (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�X�g�b�N�I�v�V�����������)
 * �X�g�b�N�I�v�V�����������
 * 
 * @@author �Ԑi
 * @@version 1.0
 */
public class WEB3AccInfoStockOptionInfo extends Message
{

    /**
     * (�����R�[�h)
     * �����R�[�h
     */
    public String productCode;

    /**
     * (�������j
     * ������
     */
    public String productName;

    /**
     * @@roseuid 44FEAB0D01E4
     */
    public WEB3AccInfoStockOptionInfo() 
    {

    }
}@
