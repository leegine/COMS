head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�N���L�[(WEB3SrvRegiExecKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�N���L�[)<BR>
 * �T�[�r�X���p�N���L�[�f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3SrvRegiExecKey extends Message 
{
    
    /**
     * (���p�L�[��ʋ敪)<BR>
     * 0:��QURL<BR>
     * 1:�n�b�V���l<BR>
     * 2:���M�p�����[�^<BR>
     * 3:���M���@@�敪<BR>
     * 4:�n�b�V���v�Z�����敪<BR>
     * 5:�n�b�V���v�Z�菇�敪<BR>
     * 6:���M�p�����[�^�敪<BR>
     * 7:�Í����ڋq�R�[�h�敪<BR>
     */
    public String keyKindDiv;
    
    /**
     * (���p�L�[ID)
     */
    public String keyId;

    /**
     * (���p�L�[)
     */
    public String key;
    
    /**
     * (�����敪)<BR>
     * true:�����@@false:�L��<BR>
     */
    public boolean invalidDiv;
    
    /**
     * (�T�[�r�X���p�N���L�[)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F1ECAA0026
     */
    public WEB3SrvRegiExecKey() 
    {
     
    }
}
@
