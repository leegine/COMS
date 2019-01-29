head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSupplierServiceManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�_�v�^�[�̊Ǘ��p���\�b�h��񋟂���T�[�r�X�N���X(WEB3QuoteDataSupplierServiceManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/07/01 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/04/28 �R�c�@@��i(FLJ) �����Ď��̊J�n�E�I��������ǉ�
                 : 2009/04/23 �V���@@�h�O(FLJ) �����V�X�e��QUICK�ւ̈ڍs
*/
package webbroker3.quoteadaptor.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Service;


/**
 * �����A�_�v�^�[�̊Ǘ��p���\�b�h��񋟂���T�[�r�X�N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3QuoteDataSupplierServiceManager extends Service
{
    /**
     * �w�肵���T�[�r�XID�̎����T�[�r�X���J�n����B
     * 
     * @@param id �T�[�r�XID
     */
    public void startService(String id);
    
    /**
     * �o�^����Ă���S�Ă̎����T�[�r�X���J�n����B
     * 
     */
    public void startAllServices();
    
    /**
     * �w�肵���T�[�r�XID�̎����T�[�r�X���~����B
     * 
     * @@param id �T�[�r�XID
     */
    public void stopService(String id);
    
    /**
     * �o�^����Ă���S�Ă̎����T�[�r�X���~����B
     */
    public void stopAllServices();
    
    /**
     * �L���b�V������Ă��鎞�������t�@@�C���ɏo�͂���B
     */
    public void dump();
    
    /**
     * �������̊Ď����J�n����B
     */
    public void startMonitoring();
    
    /**
     * �������̊Ď����I������B
     */
    public void stopMonitoring();
    
    
    /**
     * RMM�f�[�^�\�[�X���m�Őؑւ���B
     * 
     */
    public WEB3QuoteFutureData changeRMMDataSource();
    
    /**
     * TCP��RMM�̃f�[�^�\�[�X��ؑւ���B
     * 
     */
    public String switchDataSourceProtocol();
}@
