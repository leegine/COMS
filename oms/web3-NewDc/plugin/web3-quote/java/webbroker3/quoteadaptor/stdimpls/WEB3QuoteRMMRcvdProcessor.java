head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteRMMRcvdProcessor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteRMMRcvdProcessor�N���X(WEB3QuoteRMMRcvdProcessor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/04/23 �V���@@�h�O(FLJ) �V�K�쐬�i�����V�X�e��QUICK�ւ̈ڍs�j
 */
package webbroker3.quoteadaptor.stdimpls;


/**
 * (������MRMM�v���Z�b�T�[)
 * 
 * ������MRMM�v���Z�b�T�[�N���X�B
 * 
 * @@author �V��(FLJ)
 * @@version 1.0
 */
public interface WEB3QuoteRMMRcvdProcessor
{
    
    /**
     * (receive�f�[�^)
     * <BR>
     * �f�[�^���󂯎��B<BR>
     * 
     * @@param l_data �f�[�^
     */
    public void receiveData(byte[] l_data);
}
@
