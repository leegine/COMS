head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����M�������ꗗ�p�f�[�^�N���X(WEB3MutualProductCodeNameUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
                   2004/12/07 ������ (���u) �c�Ή�
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���M�����R�[�h����)
 * �����M�������ꗗ�p�f�[�^�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualProductCodeNameUnit extends Message 
{

    /**
     * �������ɑΉ����������R�[�h<BR>
     */
    public String mutualProductCode;
    
    /**
     * �����R�[�h�ɑΉ�����������<BR>
     */
    public String mutualProductName;
    
    /**
     * ���t�����敪�ꗗ<BR>
     * <BR>
     * 0:��ʁ@@1:����<BR>
     */
    public String[ ] taxTypeList;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4073BCCD0389<BR>
     */
    public WEB3MutualProductCodeNameUnit() 
    {
     
    }
}
@
