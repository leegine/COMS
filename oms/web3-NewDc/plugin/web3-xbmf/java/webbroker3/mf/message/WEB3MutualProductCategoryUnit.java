head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCategoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������J�e�S���[�R�[�h���̃f�[�^�N���X(WEB3MutualProductCategoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �����M�������J�e�S���[�R�[�h���̃f�[�^�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualProductCategoryUnit extends Message 
{

    /**
     * (���M�����J�e�S���[�R�[�h)<BR>
     * ���M�����J�e�S���[���̂ɑΉ��������M�����J�e�S���[�R�[�h<BR>
     */
    public String categoryCode;
    
    /**
     * (���M�����J�e�S���[����)<BR>
     * ���M�����J�e�S���[�R�[�h�ɑΉ��������M�����J�e�S���[����<BR>
     */
    public String categoryName;
    
    /**
     * (����J�e�S���[�R�[�h����)<BR>
     * ���Y�J�e�S���[�ɕR�t�����ʃJ�e�S���[���<BR>
     */
    public WEB3MutualProductCategoryUnit[ ] childCategory;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40AC0D240137
     */
    public WEB3MutualProductCategoryUnit() 
    {
     
    }
}
@
