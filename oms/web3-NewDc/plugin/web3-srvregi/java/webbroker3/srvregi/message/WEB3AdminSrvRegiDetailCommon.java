head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiDetailCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X���׏��ꗗ�s���ʏ��(WEB3AdminSrvRegiDetailCommon.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p�T�[�r�X���׏��ꗗ�s���ʏ��)<BR>
 * �T�[�r�X���p�T�[�r�X���׏��ꗗ�s���ʏ��f�[�^�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiDetailCommon extends Message 
{
    
    /**
     * (ID)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String serviceDiv;
    
    /**
     * (�T�[�r�X����)
     */
    public String serviceName;
    
    /**
     * (�X�e�[�^�X)<BR>
     * 0:��~���@@1:�񋟒��i�\���s�j�@@2:�񋟒�<BR>
     */
    public String serviceStatus;
    
    /**
     * (�\���\���Ԑݒ�)<BR>
     * 0�F���@@1�F�L<BR>
     */
    public String applyAbleSet;
    
    /**
     * (�T�[�r�X���p�T�[�r�X���׏��ꗗ�s���ʏ��)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE32220025
     */
    public WEB3AdminSrvRegiDetailCommon() 
    {
     
    }
}
@
