head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductConditionsGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������o�^�Ɖ�����ꗗ�s(WEB3MutualProductConditionsGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �����M�����������o�^�Ɖ�����ꗗ�s<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualProductConditionsGroup extends Message 
{
    
    /**
     * ���M����ID
     */
    public String id;
    
    /**
     * ���M�����R�[�h
     */
    public String mutualProductCode;
    
    /**
     * ���M��������R�[�h
     */
    public String mutualAssocProductCode;
    
    /**
     * ������
     */
    public String mutualProductName;
    
    /**
     * ���M�����J�e�S���[�R�[�h
     */
    public String categoryCode;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40DF77740343
     */
    public WEB3MutualProductConditionsGroup() 
    {
     
    }
}
@
