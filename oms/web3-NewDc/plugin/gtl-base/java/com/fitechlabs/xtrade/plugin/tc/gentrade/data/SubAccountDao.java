head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SubAccountDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link SubAccountDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SubAccountRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SubAccountPK 
 * @@see SubAccountRow 
 */
public class SubAccountDao extends DataAccessObject {


  /** 
   * ����{@@link SubAccountDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SubAccountRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SubAccountRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SubAccountDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SubAccountDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SubAccountRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SubAccountRow )
                return new SubAccountDao( (SubAccountRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SubAccountRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SubAccountRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SubAccountRow}�I�u�W�F�N�g 
    */
    protected SubAccountDao( SubAccountRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SubAccountRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SubAccountRow getSubAccountRow() {
        return row;
    }


  /** 
   * �w���{@@link SubAccountRow}�I�u�W�F�N�g����{@@link SubAccountDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SubAccountRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SubAccountDao}�擾�̂��߂Ɏw���{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SubAccountDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SubAccountDao forRow( SubAccountRow row ) throws java.lang.IllegalArgumentException {
        return (SubAccountDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SubAccountRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SubAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SubAccountPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SubAccountParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SubAccountRow.TYPE );
    }


  /** 
   * {@@link SubAccountRow}����ӂɓ��肷��{@@link SubAccountPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SubAccountRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SubAccountParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SubAccountPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SubAccountPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SubAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SubAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SubAccountRow findRowByPk( long p_accountId, long p_subAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubAccountPK pk = new SubAccountPK( p_accountId, p_subAccountId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SubAccountPK�I�u�W�F�N�g����{@@link SubAccountRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SubAccountPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SubAccountRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SubAccountRow findRowByPk( SubAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SubAccountRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,long)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static SubAccountDao findDaoByPk( long p_accountId, long p_subAccountId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubAccountPK pk = new SubAccountPK( p_accountId, p_subAccountId );
        SubAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SubAccountPK)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static SubAccountDao findDaoByPk( SubAccountPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SubAccountRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link SubAccountDao}�ɕR�t��{@@link SubAccountRow}���ŊO���L�[�̊֌W������{@@link InstitutionRow}���������܂��B 
   * 
   * @@return {@@link SubAccountDao}�ƊO���L�[�̊֌W�ɂ���{@@link InstitutionRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public InstitutionRow fetchInstitutionRowViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        Row row = InstitutionDao.findRowByPk( pk );
        if ( row != null && !(row instanceof InstitutionRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (InstitutionRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchInstitutionRowViaInstitutionId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public InstitutionDao fetchInstitutionDaoViaInstitutionId() throws DataNetworkException, DataFindException, DataQueryException  {
        InstitutionPK pk = new InstitutionPK( row.getInstitutionId() );
        DataAccessObject dao = InstitutionDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof InstitutionDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (InstitutionDao) dao;
    }


  /** 
   * ����{@@link SubAccountDao}�ɕR�t��{@@link SubAccountRow}���ŊO���L�[�̊֌W������{@@link BranchRow}���������܂��B 
   * 
   * @@return {@@link SubAccountDao}�ƊO���L�[�̊֌W�ɂ���{@@link BranchRow}�܂��͊O���L�[ID�̒l������null�ɐݒ�̏ꍇ��null 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */	
    public BranchRow fetchBranchRowViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getBranchIdIsNull() )
            return null;
        BranchPK pk = new BranchPK( row.getBranchId() );
        Row row = BranchDao.findRowByPk( pk );
        if ( row != null && !(row instanceof BranchRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (BranchRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchBranchRowViaBranchId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public BranchDao fetchBranchDaoViaBranchId() throws DataNetworkException, DataFindException, DataQueryException  {
        if ( row.getBranchIdIsNull() )
            return null;
        BranchPK pk = new BranchPK( row.getBranchId() );
        DataAccessObject dao = BranchDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof BranchDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (BranchDao) dao;
    }


  /** 
   * ����{@@link SubAccountDao}�ɕR�t��{@@link SubAccountRow}���ŊO���L�[�̊֌W������{@@link MainAccountRow}���������܂��B 
   * 
   * @@return {@@link SubAccountDao}�ƊO���L�[�̊֌W�ɂ���{@@link MainAccountRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public MainAccountRow fetchMainAccountRowViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        Row row = MainAccountDao.findRowByPk( pk );
        if ( row != null && !(row instanceof MainAccountRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (MainAccountRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchMainAccountRowViaAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public MainAccountDao fetchMainAccountDaoViaAccountId() throws DataNetworkException, DataFindException, DataQueryException  {
        MainAccountPK pk = new MainAccountPK( row.getAccountId() );
        DataAccessObject dao = MainAccountDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof MainAccountDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (MainAccountDao) dao;
    }


    //===========================================================================
    //
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link AssetUnitRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link AssetUnitRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchAssetUnitRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchAssetUnitRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchAssetUnitDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchAssetUnitRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchAssetUnitDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchAssetUnitDaosByAccountIdSubAccountId();
    }


  /** 
   * ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link SubAccountPreferencesRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link SubAccountPreferencesRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchSubAccountPreferencesRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return SubAccountPreferencesDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchSubAccountPreferencesRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchSubAccountPreferencesDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return SubAccountPreferencesDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchSubAccountPreferencesRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchSubAccountPreferencesDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchSubAccountPreferencesDaosByAccountIdSubAccountId();
    }


  /** 
   * ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link AssetUnitSalesRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link AssetUnitSalesRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchAssetUnitSalesRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitSalesDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchAssetUnitSalesRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchAssetUnitSalesDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetUnitSalesDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchAssetUnitSalesRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchAssetUnitSalesDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchAssetUnitSalesDaosByAccountIdSubAccountId();
    }


  /** 
   * ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link LockedAssetDetailsRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link LockedAssetDetailsRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchLockedAssetDetailsRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return LockedAssetDetailsDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchLockedAssetDetailsRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchLockedAssetDetailsDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return LockedAssetDetailsDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchLockedAssetDetailsRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchLockedAssetDetailsDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchLockedAssetDetailsDaosByAccountIdSubAccountId();
    }


  /** 
   * ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link AssetRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link AssetRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchAssetRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchAssetRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchAssetDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return AssetDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchAssetRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchAssetDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchAssetDaosByAccountIdSubAccountId();
    }


  /** 
   * ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link GenFinTransactionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link SubAccountDao}�Ɋ֘A����{@@link SubAccountRow}�̊O���L�[������{@@link GenFinTransactionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchGenFinTransactionRowsByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return GenFinTransactionDao.findRowsByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchGenFinTransactionRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchGenFinTransactionDaosByAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return GenFinTransactionDao.findDaosByAccountIdSubAccountId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchGenFinTransactionRowsByAccountIdSubAccountId()}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public List fetchGenFinTransactionDaosAccountIdSubAccountId() throws DataNetworkException, DataQueryException  {
        return fetchGenFinTransactionDaosByAccountIdSubAccountId();
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for Institution
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByInstitutionId(InstitutionRow)}���g�p���Ă��������B 
   */
    public static List findRowsByInstitutionId( InstitutionDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( dao.getInstitutionRow() );
    }


  /** 
   * {@@link InstitutionRow}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link InstitutionRow}�I�u�W�F�N�g 
   * @@return �w���{@@link InstitutionRow}�ɊO���L�[������{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( row.getInstitutionId() );
    }


  /** 
   * {@@link InstitutionPK}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link InstitutionPK}�I�u�W�F�N�g 
   * @@return {@@link InstitutionPK}�ƊO���L�[����v����l������{@@link SubAccountRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionId( pk.institution_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link SubAccountRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionId �����Ώۂł���p_institutionId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionId( long p_institutionId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "institution_id=?",
            null,
            new Object[] { new Long(p_institutionId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Institution
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByInstitutionId(InstitutionRow)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(InstitutionRow)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(InstitutionPK)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( InstitutionPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( pk.institution_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionId(long)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionId( long p_institutionId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionId( p_institutionId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for Branch
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByBranchId(BranchRow)}���g�p���Ă��������B 
   */
    public static List findRowsByBranchId( BranchDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( dao.getBranchRow() );
    }


  /** 
   * {@@link BranchRow}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link BranchRow}�I�u�W�F�N�g 
   * @@return �w���{@@link BranchRow}�ɊO���L�[������{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( row.getBranchId() );
    }


  /** 
   * {@@link BranchPK}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link BranchPK}�I�u�W�F�N�g 
   * @@return {@@link BranchPK}�ƊO���L�[����v����l������{@@link SubAccountRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByBranchId( pk.branch_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link SubAccountRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByBranchId( long p_branchId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "branch_id=?",
            null,
            new Object[] { new Long(p_branchId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for Branch
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchRow)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(BranchPK)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( BranchPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( pk.branch_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByBranchId(long)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByBranchId( long p_branchId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByBranchId( p_branchId ) );
    }


    //-----------------------------------------------------------
    // Find Rows given foreign key values for MainAccount
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByAccountId(MainAccountRow)}���g�p���Ă��������B 
   */
    public static List findRowsByAccountId( MainAccountDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( dao.getMainAccountRow() );
    }


  /** 
   * {@@link MainAccountRow}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link MainAccountRow}�I�u�W�F�N�g 
   * @@return �w���{@@link MainAccountRow}�ɊO���L�[������{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( row.getAccountId() );
    }


  /** 
   * {@@link MainAccountPK}�ƊO���L�[�̊֌W�ɂ���{@@link SubAccountRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link MainAccountPK}�I�u�W�F�N�g 
   * @@return {@@link MainAccountPK}�ƊO���L�[����v����l������{@@link SubAccountRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByAccountId( pk.account_id );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link SubAccountRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountId( long p_accountId  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=?",
            null,
            new Object[] { new Long(p_accountId) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for MainAccount
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountRow)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(MainAccountPK)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( MainAccountPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( pk.account_id ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountId(long)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountId( long p_accountId ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByAccountId( p_accountId ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, and �ɂĎw��̒l�����ӂ�{@@link SubAccountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, and �̒l�ƈ�v����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SubAccountRow findRowByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=? and sub_account_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubAccountDao.findRowsByAccountIdSubAccountId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdSubAccountId(long, long)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static SubAccountDao findDaoByAccountIdSubAccountId( long p_accountId, long p_subAccountId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountId( p_accountId, p_subAccountId ) );
    }


  /** 
   * p_accountId, p_subAccountType, and �ɂĎw��̒l�����ӂ�{@@link SubAccountRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountType �����Ώۂł���p_subAccountType�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountType, and �̒l�ƈ�v����{@@link SubAccountRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SubAccountRow findRowByAccountIdSubAccountType( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=? and sub_account_type=?",
            null,
            new Object[] { new Long(p_accountId), p_subAccountType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubAccountRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubAccountDao.findRowsByAccountIdSubAccountType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdSubAccountType(long, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static SubAccountDao findDaoByAccountIdSubAccountType( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdSubAccountType( p_accountId, p_subAccountType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_accountId, p_subAccountId, p_subAccountType, p_institutionCode, p_branchId, and �ɂĎw��̒l�Ɉ�v����{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_subAccountId �����Ώۂł���p_subAccountId�t�B�[���h�̒l
   * @@param p_subAccountType �����Ώۂł���p_subAccountType�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_subAccountId, p_subAccountType, p_institutionCode, p_branchId, and �̒l�ƈ�v����{@@link SubAccountRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType, String p_institutionCode, Long p_branchId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            SubAccountRow.TYPE,
            "account_id=? and sub_account_id=? and sub_account_type=? and institution_code=? and branch_id=?",
            null,
            new Object[] { new Long(p_accountId), new Long(p_subAccountId), p_subAccountType, p_institutionCode, p_branchId } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId(long, long, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum, String, Long)}�����{@@link #forRow(SubAccountRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId( long p_accountId, long p_subAccountId, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum p_subAccountType, String p_institutionCode, Long p_branchId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdSubAccountIdSubAccountTypeInstitutionCodeBranchId( p_accountId, p_subAccountId, p_subAccountType, p_institutionCode, p_branchId ) );
    }

}
@
