head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSource.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor;

import java.util.List;

/**
 * �V�X�e�����ł̎������̋�������\���C���^�[�t�F�C�X�B<br>
 *
 * @@author Takuji Yamada
 * @@author Yoshihara Tadafumi
 * @@version 1.0
 */
public interface WEB3QuoteDataSource
{

    /**
     * �������̒ʒm�C�x���g����������n���h����o�^����B
     *
     * @@param handler �������̒ʒm�C�x���g����������n���h���B
     */
    public void registerHandler(WEB3QuoteEventHandler handler);

    /**
     * �������̋������J�n����B
     *
     */
    public void start();

    /**
     * �������̋������~����B
     *
     */
    public void stop();
    
    /**
     * �T�[�r�XID���擾����B
     * 
     */
    public String getServiceId();
    
    /**
     * �T�[�r�X�X�^�[�g�ς��擾����B
     * 
     */
    public boolean isStarted();
    
    /**
     * �����T�[�o�ꗗ���擾����B
     * 
     */
    public List getFeeders();

}
@
