head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualDisplayOrderGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������o�^�ꗗ�s(WEB3MutualDisplayOrderGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���M�Ǘ��Җ����\�������o�^�ꗗ�s)<BR>
 * �����M���Ǘ��Җ����\�������o�^�ꗗ�s�f�[�^�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualDisplayOrderGroup extends Message 
{   
    /**
     * (�\����)<BR>
     *  �\����
     */
    public String displayOrder;
    
    /**
     * (�����R�[�h)<BR>
     *  �����R�[�h
     */
    public String mutualProductCode;
    
    /**
     * (���M��������R�[�h)<BR>
     *  ���M��������R�[�h
     */
    public String mutualAssocProductCode;
    
    /**
     * (������)<BR>
     *  ������
     */
    public String mutualProductName;
    
    /**
     * (���M�����J�e�S���[�R�[�h�P)<BR>
     *  ���M�����J�e�S���[�R�[�h�P
     */
    public String categoryCode1;
    
    /**
     * (���M�����J�e�S���[���̂P)<BR>
     *  ���M�����J�e�S���[���̂P
     */
    public String categoryName1;
    
    /**
     * (���M�����J�e�S���[�R�[�h�Q)<BR>
     *  ���M�����J�e�S���[�R�[�h�Q
     */
    public String categoryCode2;
    
    /**
     * (���M�����J�e�S���[���̂Q)<BR>
     *  ���M�����J�e�S���[���̂Q
     */
    public String categoryName2;
    
    /**
     * (���M�����J�e�S���[�R�[�h�R)<BR>
     *  ���M�����J�e�S���[�R�[�h�R
     */
    public String categoryCode3;
    
    /**
     * (���M�����J�e�S���[���̂R)<BR>
     *  ���M�����J�e�S���[���̂R
     */
    public String categoryName3;
    
    /**
     * (������t���؎���)<BR>
     *  ������t���؎���<BR>
     *  "HH�FMM" (24���Ԍ`���œn�����j
     */
    public String orderCloseTime;
    
    /**
     * (���M�Ǘ��Җ����\�������o�^�ꗗ�s)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4153BBC9019E
     */
    public WEB3MutualDisplayOrderGroup()
    {
    }
}
@
